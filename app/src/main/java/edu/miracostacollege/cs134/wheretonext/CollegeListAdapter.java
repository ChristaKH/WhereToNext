package edu.miracostacollege.cs134.wheretonext;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.miracostacollege.cs134.wheretonext.model.College;

/**
 * Helper class to provide custom adapter for the <code>College</code> list.
 */
public class CollegeListAdapter extends ArrayAdapter<College> {

    private Context mContext;
    private List<College> mCollegesList = new ArrayList<>();
    private int mResourceId;
    private NumberFormat numFormat = NumberFormat.getInstance(Locale.US);
    private NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);

    /**
     * Creates a new <code>CollegeListAdapter</code> given a mContext, resource id and list of colleges.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param colleges The list of colleges to display
     */
    public CollegeListAdapter(Context c, int rId, List<College> colleges) {
        super(c, rId, colleges);
        mContext = c;
        mResourceId = rId;
        mCollegesList = colleges;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the College selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {


        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);
        view.setTag(pos);

        // DONE:  Write the code to correctly inflate the view (college_list_item) with
        // DONE:  all widgets filled with the appropriate College information.
        // Inflate our custom layout with data from the List
        College focusedCollege = mCollegesList.get(pos);

        // Fill the view
        ImageView collegeDetailsImageView = view.findViewById(R.id.collegeDetailsImageView);
        TextView collegeDetailsNameTextView = view.findViewById(R.id.collegeDetailsNameTextView);
        TextView collegeDetailsPopulationTextView = view.findViewById(R.id.collegeDetailsPopulationTextView);
        TextView collegeDetailsTuitionTextView = view.findViewById(R.id.collegeDetailsTuitionTextView);
        RatingBar collegeDetailsRatingBar = view.findViewById(R.id.collegeDetailsRatingBar);

        // Put info into text views
        collegeDetailsNameTextView.setText(focusedCollege.getName());
        collegeDetailsPopulationTextView.setText(numFormat.format(focusedCollege.getPopulation()));
        collegeDetailsTuitionTextView.setText(currency.format(focusedCollege.getTuition()));
        collegeDetailsRatingBar.setRating((float)focusedCollege.getRating());

        // Load the image dynamically
        AssetManager am = mContext.getAssets();
        try{
            InputStream stream = am.open(focusedCollege.getImageName());
            Drawable image = Drawable.createFromStream(stream, focusedCollege.getImageName());

            // Put image into image view
            collegeDetailsImageView.setImageDrawable(image);
        } catch(IOException e){
            Log.e("Where To Next", e.getMessage());
        }

        return view;
    }
}
