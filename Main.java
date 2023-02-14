import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        
        // open key file - create first hashtable

        try {
            ArrayList<Player> list;
            Hashtable<String, String> ht1 = new Hashtable<String, String>();    // holds keyfile data
            Hashtable<String, Player> ht2 = new Hashtable<String, Player>();    // holds player names and the corresponding object
            String filename;


            Scanner scnr = new Scanner(System.in); // gets input from keyboard

            filename = "keyfile.txt"; // read in file name to open
            File inFS = new File(filename); // create file object
            Scanner inputFile = new Scanner(inFS); // scanner for input from file


            getKeyFileData(inputFile, ht1); // call function to parse keyfile.txt and create hashtable

            
            filename = scnr.nextLine(); // read in file name to open
            inFS = new File(filename); // create file object
            inputFile = new Scanner(inFS); // scanner for input from player data file


            list = getFileData(inputFile, ht1, ht2);    // parses player data file, creates/updates player objects, returns list of player objects


            list.sort(null);    // sorts the list by names alphabetically

            // display team stats
            System.out.println("AWAY");
            for(Player x : list)
            {
                if(!x.homeTeam)
                System.out.print(x);
            }
            System.out.println("\nHOME");
            for(Player x : list)
            {
                if(x.homeTeam)
                System.out.print(x);
            }

            System.out.println("\nLEAGUE LEADERS");


            // sort by leaders

            /*******************************************************
             *                    BA Leader                        *
             *******************************************************/
            System.out.println("BATTING AVERAGE");

            Collections.sort(list, Comparator.comparing(Player::getBattingAvg));
            Collections.reverse(list);

            //System.out.println(list);

            double max = list.get(0).getBattingAvg();
            double mid = 0.0;
            double low = 0.0;            
            int i = 1;
            int count = 1;
            String x = "";
            String y = "";

            ArrayList<String> firstA = new ArrayList<>();
            ArrayList<String> firstH = new ArrayList<>();


            if(!list.get(0).homeTeam)
            {
                firstA.add(list.get(0).name);            // add the first player in the sorted list to the first place arrayList (either home or away)
            }
            else
            {
                firstH.add(list.get(0).name);
            }

            while(i < list.size())
            {
                if(Math.abs(max - list.get(i).getBattingAvg()) < 0.000001)  // same as greatest value
                {
                    if(!list.get(i).homeTeam)
                    {
                        //System.out.println(list.get(i).name + " is away team");
                        firstA.add(list.get(i).name);
                        count++;
                    }
                    else
                    {
                        //System.out.println(list.get(i).name + " is home team");
                        firstH.add(list.get(i).name);
                        count++;
                    }
                }
                else
                {
                    break;
                }
                i++;
            }

            // first place output

            if(!firstA.isEmpty())
            {
                Collections.sort(firstA);
                x = firstA.toString().replace("]", "");
                x = x.toString().replace("[", "");

                if(firstH.isEmpty())
                {
                    System.out.println(String.format("%.3f", max) + "\t" + x);
                }
            }
            if(!firstH.isEmpty())
            {
                Collections.sort(firstH);

                y = firstH.toString().replace("]", "");
                y = y.toString().replace("[", "");

                if(!firstA.isEmpty())
                {
                    x += ", ";
                    System.out.println(String.format("%.3f", max) + "\t" + x + y);
                }
                else
                {
                    System.out.println(String.format("%.3f", max) + "\t" + y);
                }      
            }
            
            

            // second place leaders 

            mid = list.get(i).getBattingAvg();
            if(count < 3)
            {
                ArrayList<String> secondA = new ArrayList<>();
                ArrayList<String> secondH = new ArrayList<>();

                while(i < list.size())
                {
                    if(Math.abs(mid - list.get(i).getBattingAvg()) < 0.000001)
                    {
                        if(!list.get(i).homeTeam)
                        {
                            secondA.add(list.get(i).name);      // either add player to home or away team list for output
                            count++;
                        }
                        else
                        {
                            secondH.add(list.get(i).name);
                            count++;
                        }
                    }
                    else
                    {
                        break;
                    }
                    i++;
                }

                // second place output

                if(!secondA.isEmpty())
                {
                    Collections.sort(secondA);
                    x = secondA.toString().replace("]", "");
                    x = x.toString().replace("[", "");

                    if(secondH.isEmpty())
                    {
                        System.out.println(String.format("%.3f", mid) + "\t" + x);
                    }
                }
                if(!secondH.isEmpty())
                {
                    Collections.sort(secondH);
                    y = secondH.toString().replace("]", "");
                    y = y.toString().replace("[", "");

                    if(!secondA.isEmpty())
                    {
                        x += ", ";
                        System.out.println(String.format("%.3f", mid) + "\t" + x + y);
                    }
                    else
                    {
                        System.out.println(String.format("%.3f", mid) + "\t" + y);
                    }      
                }

                // third place leaders

                low = list.get(i).getBattingAvg();
                if(count < 3)
                {
                    ArrayList<String> thirdA = new ArrayList<>();
                    ArrayList<String> thirdH = new ArrayList<>();

                    while(i < list.size())
                    {
                        if(Math.abs(low - list.get(i).getBattingAvg()) < 0.000001)
                        {
                            if(!list.get(i).homeTeam)
                            {
                                thirdA.add(list.get(i).name);      // either add player to home or away team list for output
                                count++;
                            }
                            else
                            {
                                thirdH.add(list.get(i).name);
                                count++;
                            }
                        }
                        else
                        {
                            break;
                        }
                        i++;
                    }

                    if(!thirdA.isEmpty())
                    {
                        Collections.sort(thirdA);
                        x = thirdA.toString().replace("]", "");
                        x = x.toString().replace("[", "");
    
                        if(thirdH.isEmpty())
                        {
                            System.out.println(String.format("%.3f", low) + "\t" + x);
                        }
                    }
                    if(!thirdH.isEmpty())
                    {
                        Collections.sort(thirdH);
                        y = thirdH.toString().replace("]", "");
                        y = y.toString().replace("[", "");
    
                        if(!thirdA.isEmpty())
                        {
                            x += ", ";
                            System.out.println(String.format("%.3f", low) + "\t" + x + y);
                        }
                        else
                        {
                            System.out.println(String.format("%.3f", low) + "\t" + y);
                        }      
                    }

                    // empty lists to be reused
                    firstA.clear();
                    firstH.clear();

                    secondA.clear();
                    secondH.clear();

                    thirdA.clear();  
                    thirdH.clear();
                }
                else
                {
                    // empty lists to be reused
                    firstA.clear();
                    firstH.clear();

                    secondA.clear();
                    secondH.clear();
                }
            }
            else
            {
                firstA.clear();
                firstH.clear();
            }




            /*******************************************************
             *                    OB% Leader                       *
             *******************************************************/
            System.out.println("\nON-BASE PERCENTAGE");

            Collections.sort(list, Comparator.comparing(Player::getOnBasePercent));
            Collections.reverse(list);

            //System.out.println(list);

            max = list.get(0).getOnBasePercent();
            mid = 0.0;
            low = 0.0;            
            i = 1;
            count = 1;
            x = "";
            y = "";

            if(!list.get(0).homeTeam)
            {
                firstA.add(list.get(0).name);            // add the first player in the sorted list to the first place arrayList (either home or away)
            }
            else
            {
                firstH.add(list.get(0).name);
            }

            while(i < list.size())
            {
                if(Math.abs(max - list.get(i).getOnBasePercent()) < 0.000001)  // same as greatest value
                {
                    if(!list.get(i).homeTeam)
                    {
                        //System.out.println(list.get(i).name + " is away team");
                        firstA.add(list.get(i).name);
                        count++;
                    }
                    else
                    {
                        //System.out.println(list.get(i).name + " is home team");
                        firstH.add(list.get(i).name);
                        count++;
                    }
                }
                else
                {
                    break;
                }
                i++;
            }

            // first place output
            
            if(!firstA.isEmpty())
            {
                Collections.sort(firstA);
                x = firstA.toString().replace("]", "");
                x = x.toString().replace("[", "");

                if(firstH.isEmpty())
                {
                    System.out.println(String.format("%.3f", max) + "\t" + x);
                }
            }
            if(!firstH.isEmpty())
            {
                Collections.sort(firstH);

                y = firstH.toString().replace("]", "");
                y = y.toString().replace("[", "");

                if(!firstA.isEmpty())
                {
                    x += ", ";
                    System.out.println(String.format("%.3f", max) + "\t" + x + y);
                }
                else
                {
                    System.out.println(String.format("%.3f", max) + "\t" + y);
                }      
            }
            
            

            // second place leaders 

            mid = list.get(i).getOnBasePercent();
            if(count < 3)
            {
                ArrayList<String> secondA = new ArrayList<>();
                ArrayList<String> secondH = new ArrayList<>();

                while(i < list.size())
                {
                    if(Math.abs(mid - list.get(i).getOnBasePercent()) < 0.000001)
                    {
                        if(!list.get(i).homeTeam)
                        {
                            secondA.add(list.get(i).name);      // either add player to home or away team list for output
                            count++;
                        }
                        else
                        {
                            secondH.add(list.get(i).name);
                            count++;
                        }
                    }
                    else
                    {
                        break;
                    }
                    i++;
                }

                // second place output

                if(!secondA.isEmpty())
                {
                    Collections.sort(secondA);
                    x = secondA.toString().replace("]", "");
                    x = x.toString().replace("[", "");

                    if(secondH.isEmpty())
                    {
                        System.out.println(String.format("%.3f", mid) + "\t" + x);
                    }
                }
                if(!secondH.isEmpty())
                {
                    Collections.sort(secondH);
                    y = secondH.toString().replace("]", "");
                    y = y.toString().replace("[", "");

                    if(!secondA.isEmpty())
                    {
                        x += ", ";
                        System.out.println(String.format("%.3f", mid) + "\t" + x + y);
                    }
                    else
                    {
                        System.out.println(String.format("%.3f", mid) + "\t" + y);
                    }      
                }

                // third place leaders

                low = list.get(i).getOnBasePercent();
                if(count < 3)
                {
                    ArrayList<String> thirdA = new ArrayList<>();
                    ArrayList<String> thirdH = new ArrayList<>();

                    while(i < list.size())
                    {
                        if(Math.abs(low - list.get(i).getOnBasePercent()) < 0.000001)
                        {
                            if(!list.get(i).homeTeam)
                            {
                                thirdA.add(list.get(i).name);      // either add player to home or away team list for output
                                count++;
                            }
                            else
                            {
                                thirdH.add(list.get(i).name);
                                count++;
                            }
                        }
                        else
                        {
                            break;
                        }
                        i++;
                    }

                    if(!thirdA.isEmpty())
                    {
                        Collections.sort(thirdA);
                        x = thirdA.toString().replace("]", "");
                        x = x.toString().replace("[", "");
    
                        if(thirdH.isEmpty())
                        {
                            System.out.println(String.format("%.3f", low) + "\t" + x);
                        }
                    }
                    if(!thirdH.isEmpty())
                    {
                        Collections.sort(thirdH);
                        y = thirdH.toString().replace("]", "");
                        y = y.toString().replace("[", "");
    
                        if(!thirdA.isEmpty())
                        {
                            x += ", ";
                            System.out.println(String.format("%.3f", low) + "\t" + x + y);
                        }
                        else
                        {
                            System.out.println(String.format("%.3f", low) + "\t" + y);
                        }      
                    }

                    // empty lists to be reused
                    firstA.clear();
                    firstH.clear();

                    secondA.clear();
                    secondH.clear();

                    thirdA.clear();  
                    thirdH.clear();
                }
                else
                {
                    // empty lists to be reused
                    firstA.clear();
                    firstH.clear();

                    secondA.clear();
                    secondH.clear();
                }
            }
            else
            {
                firstA.clear();
                firstH.clear();
            }



            /*******************************************************
             *                   Hit Leader                        *
             *******************************************************/
            System.out.println("\nHITS");

            Collections.sort(list, Comparator.comparing(Player::getHit));
            Collections.reverse(list);

            //System.out.println(list);

            int max1 = list.get(0).hit;
            int mid1 = 0;
            int low1 = 0;            
            i = 1;
            count = 1;
            x = "";
            y = "";

           
            if(!list.get(0).homeTeam)
            {
                firstA.add(list.get(0).name);            // add the first player in the sorted list to the first place arrayList (either home or away)
            }
            else
            {
                firstH.add(list.get(0).name);
            }

            while(i < list.size())
            {
                if(max1 == list.get(i).hit)  // same as greatest value
                {
                    if(!list.get(i).homeTeam)
                    {
                        //System.out.println(list.get(i).name + " is away team");
                        firstA.add(list.get(i).name);
                        count++;
                    }
                    else
                    {
                        //System.out.println(list.get(i).name + " is home team");
                        firstH.add(list.get(i).name);
                        count++;
                    }
                }
                else
                {
                    break;
                }
                i++;
            }

            // first place output
            
            if(!firstA.isEmpty())
            {
                Collections.sort(firstA);
                x = firstA.toString().replace("]", "");
                x = x.toString().replace("[", "");

                if(firstH.isEmpty())
                {
                    System.out.println(max1 + "\t" + x);
                }
            }
            if(!firstH.isEmpty())
            {
                Collections.sort(firstH);

                y = firstH.toString().replace("]", "");
                y = y.toString().replace("[", "");

                if(!firstA.isEmpty())
                {
                    x += ", ";
                    System.out.println(max1 + "\t" + x + y);
                }
                else
                {
                    System.out.println(max1 + "\t" + y);
                }      
            }
            
            

            // second place leaders 

            
            if(count < 3)
            {
                mid1 = list.get(i).hit;
                ArrayList<String> secondA = new ArrayList<>();
                ArrayList<String> secondH = new ArrayList<>();

                while(i < list.size())
                {
                    if(mid1 == list.get(i).hit)
                    {
                        if(!list.get(i).homeTeam)
                        {
                            secondA.add(list.get(i).name);      // either add player to home or away team list for output
                            count++;
                        }
                        else
                        {
                            secondH.add(list.get(i).name);
                            count++;
                        }
                    }
                    else
                    {
                        break;
                    }
                    i++;
                }

                // second place output

                if(!secondA.isEmpty())
                {
                    Collections.sort(secondA);
                    x = secondA.toString().replace("]", "");
                    x = x.toString().replace("[", "");

                    if(secondH.isEmpty())
                    {
                        System.out.println(mid1 + "\t" + x);
                    }
                }
                if(!secondH.isEmpty())
                {
                    Collections.sort(secondH);
                    y = secondH.toString().replace("]", "");
                    y = y.toString().replace("[", "");

                    if(!secondA.isEmpty())
                    {
                        x += ", ";
                        System.out.println(mid1 + "\t" + x + y);
                    }
                    else
                    {
                        System.out.println(mid1 + "\t" + y);
                    }      
                }

                // third place leaders                
                if(count < 3)
                {
                    low1 = list.get(i).hit;
                    ArrayList<String> thirdA = new ArrayList<>();
                    ArrayList<String> thirdH = new ArrayList<>();

                    while(i < list.size())
                    {
                        if(low1 == list.get(i).hit)
                        {
                            if(!list.get(i).homeTeam)
                            {
                                thirdA.add(list.get(i).name);      // either add player to home or away team list for output
                                count++;
                            }
                            else
                            {
                                thirdH.add(list.get(i).name);
                                count++;
                            }
                        }
                        else
                        {
                            break;
                        }
                        i++;
                    }

                    if(!thirdA.isEmpty())
                    {
                        Collections.sort(thirdA);
                        x = thirdA.toString().replace("]", "");
                        x = x.toString().replace("[", "");
    
                        if(thirdH.isEmpty())
                        {
                            System.out.println(low1 + "\t" + x);
                        }
                    }
                    if(!thirdH.isEmpty())
                    {
                        Collections.sort(thirdH);
                        y = thirdH.toString().replace("]", "");
                        y = y.toString().replace("[", "");
    
                        if(!thirdA.isEmpty())
                        {
                            x += ", ";
                            System.out.println(low1 + "\t" + x + y);
                        }
                        else
                        {
                            System.out.println(low1 + "\t" + y);
                        }      
                    }

                    // empty lists to be reused
                    firstA.clear();
                    firstH.clear();

                    secondA.clear();
                    secondH.clear();

                    thirdA.clear();  
                    thirdH.clear();
                }
                else
                {
                    // empty lists to be reused
                    firstA.clear();
                    firstH.clear();

                    secondA.clear();
                    secondH.clear();
                }
            }
            else
            {
                firstA.clear();
                firstH.clear();
            }


            /*******************************************************
             *                   Walk Leader                        *
             *******************************************************/
            System.out.println("\nWALKS");

            Collections.sort(list, Comparator.comparing(Player::getWalk));
            Collections.reverse(list);

            //System.out.println(list);

            max1 = list.get(0).walk;
            mid1 = 0;
            low1 = 0;            
            i = 1;
            count = 1;
            x = "";
            y = "";

           
            if(!list.get(0).homeTeam)
            {
                firstA.add(list.get(0).name);            // add the first player in the sorted list to the first place arrayList (either home or away)
            }
            else
            {
                firstH.add(list.get(0).name);
            }

            while(i < list.size())
            {
                if(max1 == list.get(i).walk)  // same as greatest value
                {
                    if(!list.get(i).homeTeam)
                    {
                        //System.out.println(list.get(i).name + " is away team");
                        firstA.add(list.get(i).name);
                        count++;
                    }
                    else
                    {
                        //System.out.println(list.get(i).name + " is home team");
                        firstH.add(list.get(i).name);
                        count++;
                    }
                }
                else
                {
                    break;
                }
                i++;
            }

            // first place output
            
            if(!firstA.isEmpty())
            {
                Collections.sort(firstA);
                x = firstA.toString().replace("]", "");
                x = x.toString().replace("[", "");

                if(firstH.isEmpty())
                {
                    System.out.println(max1 + "\t" + x);
                }
            }
            if(!firstH.isEmpty())
            {
                Collections.sort(firstH);

                y = firstH.toString().replace("]", "");
                y = y.toString().replace("[", "");

                if(!firstA.isEmpty())
                {
                    x += ", ";
                    System.out.println(max1 + "\t" + x + y);
                }
                else
                {
                    System.out.println(max1 + "\t" + y);
                }      
            }
            
            

            // second place leaders 

            
            if(count < 3)
            {
                mid1 = list.get(i).walk;
                ArrayList<String> secondA = new ArrayList<>();
                ArrayList<String> secondH = new ArrayList<>();

                while(i < list.size())
                {
                    if(mid1 == list.get(i).walk)
                    {
                        if(!list.get(i).homeTeam)
                        {
                            secondA.add(list.get(i).name);      // either add player to home or away team list for output
                            count++;
                        }
                        else
                        {
                            secondH.add(list.get(i).name);
                            count++;
                        }
                    }
                    else
                    {
                        break;
                    }
                    i++;
                }

                // second place output

                if(!secondA.isEmpty())
                {
                    Collections.sort(secondA);
                    x = secondA.toString().replace("]", "");
                    x = x.toString().replace("[", "");

                    if(secondH.isEmpty())
                    {
                        System.out.println(mid1 + "\t" + x);
                    }
                }
                if(!secondH.isEmpty())
                {
                    Collections.sort(secondH);
                    y = secondH.toString().replace("]", "");
                    y = y.toString().replace("[", "");

                    if(!secondA.isEmpty())
                    {
                        x += ", ";
                        System.out.println(mid1 + "\t" + x + y);
                    }
                    else
                    {
                        System.out.println(mid1 + "\t" + y);
                    }      
                }

                // third place leaders                
                if(count < 3)
                {
                    low1 = list.get(i).walk;
                    ArrayList<String> thirdA = new ArrayList<>();
                    ArrayList<String> thirdH = new ArrayList<>();

                    while(i < list.size())
                    {
                        if(low1 == list.get(i).walk)
                        {
                            if(!list.get(i).homeTeam)
                            {
                                thirdA.add(list.get(i).name);      // either add player to home or away team list for output
                                count++;
                            }
                            else
                            {
                                thirdH.add(list.get(i).name);
                                count++;
                            }
                        }
                        else
                        {
                            break;
                        }
                        i++;
                    }

                    if(!thirdA.isEmpty())
                    {
                        Collections.sort(thirdA);
                        x = thirdA.toString().replace("]", "");
                        x = x.toString().replace("[", "");
    
                        if(thirdH.isEmpty())
                        {
                            System.out.println(low1 + "\t" + x);
                        }
                    }
                    if(!thirdH.isEmpty())
                    {
                        Collections.sort(thirdH);
                        y = thirdH.toString().replace("]", "");
                        y = y.toString().replace("[", "");
    
                        if(!thirdA.isEmpty())
                        {
                            x += ", ";
                            System.out.println(low1 + "\t" + x + y);
                        }
                        else
                        {
                            System.out.println(low1 + "\t" + y);
                        }      
                    }

                    // empty lists to be reused
                    firstA.clear();
                    firstH.clear();

                    secondA.clear();
                    secondH.clear();

                    thirdA.clear();  
                    thirdH.clear();
                }
                else
                {
                    // empty lists to be reused
                    firstA.clear();
                    firstH.clear();

                    secondA.clear();
                    secondH.clear();
                }
            }
            else
            {
                firstA.clear();
                firstH.clear();
            }


            /*******************************************************
             *                   Strikeout Leader                  *
             *******************************************************/
            System.out.println("\nSTRIKEOUTS");

            Collections.sort(list, Comparator.comparing(Player::getStrikeout));
            //Collections.reverse(list);

            //System.out.println(list);

            max1 = list.get(0).strikeout;
            mid1 = 0;
            low1 = 0;            
            i = 1;
            count = 1;
            x = "";
            y = "";

           
            if(!list.get(0).homeTeam)
            {
                firstA.add(list.get(0).name);            // add the first player in the sorted list to the first place arrayList (either home or away)
            }
            else
            {
                firstH.add(list.get(0).name);
            }

            while(i < list.size())
            {
                if(max1 == list.get(i).strikeout)  // same as greatest value
                {
                    if(!list.get(i).homeTeam)
                    {
                        //System.out.println(list.get(i).name + " is away team");
                        firstA.add(list.get(i).name);
                        count++;
                    }
                    else
                    {
                        //System.out.println(list.get(i).name + " is home team");
                        firstH.add(list.get(i).name);
                        count++;
                    }
                }
                else
                {
                    break;
                }
                i++;
            }

            // first place output
            
            if(!firstA.isEmpty())
            {
                Collections.sort(firstA);
                x = firstA.toString().replace("]", "");
                x = x.toString().replace("[", "");

                if(firstH.isEmpty())
                {
                    System.out.println(max1 + "\t" + x);
                }
            }
            if(!firstH.isEmpty())
            {
                Collections.sort(firstH);

                y = firstH.toString().replace("]", "");
                y = y.toString().replace("[", "");

                if(!firstA.isEmpty())
                {
                    x += ", ";
                    System.out.println(max1 + "\t" + x + y);
                }
                else
                {
                    System.out.println(max1 + "\t" + y);
                }      
            }
            
            

            // second place leaders 

            
            if(count < 3)
            {
                mid1 = list.get(i).strikeout;
                ArrayList<String> secondA = new ArrayList<>();
                ArrayList<String> secondH = new ArrayList<>();

                while(i < list.size())
                {
                    if(mid1 == list.get(i).strikeout)
                    {
                        if(!list.get(i).homeTeam)
                        {
                            secondA.add(list.get(i).name);      // either add player to home or away team list for output
                            count++;
                        }
                        else
                        {
                            secondH.add(list.get(i).name);
                            count++;
                        }
                    }
                    else
                    {
                        break;
                    }
                    i++;
                }

                // second place output

                if(!secondA.isEmpty())
                {
                    Collections.sort(secondA);
                    x = secondA.toString().replace("]", "");
                    x = x.toString().replace("[", "");

                    if(secondH.isEmpty())
                    {
                        System.out.println(mid1 + "\t" + x);
                    }
                }
                if(!secondH.isEmpty())
                {
                    Collections.sort(secondH);
                    y = secondH.toString().replace("]", "");
                    y = y.toString().replace("[", "");

                    if(!secondA.isEmpty())
                    {
                        x += ", ";
                        System.out.println(mid1 + "\t" + x + y);
                    }
                    else
                    {
                        System.out.println(mid1 + "\t" + y);
                    }      
                }

                // third place leaders                
                if(count < 3)
                {
                    low1 = list.get(i).strikeout;
                    ArrayList<String> thirdA = new ArrayList<>();
                    ArrayList<String> thirdH = new ArrayList<>();

                    while(i < list.size())
                    {
                        if(low1 == list.get(i).strikeout)
                        {
                            if(!list.get(i).homeTeam)
                            {
                                thirdA.add(list.get(i).name);      // either add player to home or away team list for output
                                count++;
                            }
                            else
                            {
                                thirdH.add(list.get(i).name);
                                count++;
                            }
                        }
                        else
                        {
                            break;
                        }
                        i++;
                    }

                    if(!thirdA.isEmpty())
                    {
                        Collections.sort(thirdA);
                        x = thirdA.toString().replace("]", "");
                        x = x.toString().replace("[", "");
    
                        if(thirdH.isEmpty())
                        {
                            System.out.println(low1 + "\t" + x);
                        }
                    }
                    if(!thirdH.isEmpty())
                    {
                        Collections.sort(thirdH);
                        y = thirdH.toString().replace("]", "");
                        y = y.toString().replace("[", "");
    
                        if(!thirdA.isEmpty())
                        {
                            x += ", ";
                            System.out.println(low1 + "\t" + x + y);
                        }
                        else
                        {
                            System.out.println(low1 + "\t" + y);
                        }      
                    }

                    // empty lists to be reused
                    firstA.clear();
                    firstH.clear();

                    secondA.clear();
                    secondH.clear();

                    thirdA.clear();  
                    thirdH.clear();
                }
                else
                {
                    // empty lists to be reused
                    firstA.clear();
                    firstH.clear();

                    secondA.clear();
                    secondH.clear();
                }
            }
            else
            {
                firstA.clear();
                firstH.clear();
            }


            /*******************************************************
             *                   hitBy Leader                        *
             *******************************************************/
            System.out.println("\nHIT BY PITCH");

            Collections.sort(list, Comparator.comparing(Player::getHitBy));
            Collections.reverse(list);

            //System.out.println(list);

            max1 = list.get(0).hitBy;
            mid1 = 0;
            low1 = 0;            
            i = 1;
            count = 1;
            x = "";
            y = "";

           
            if(!list.get(0).homeTeam)
            {
                firstA.add(list.get(0).name);            // add the first player in the sorted list to the first place arrayList (either home or away)
            }
            else
            {
                firstH.add(list.get(0).name);
            }

            while(i < list.size())
            {
                if(max1 == list.get(i).hitBy)  // same as greatest value
                {
                    if(!list.get(i).homeTeam)
                    {
                        //System.out.println(list.get(i).name + " is away team");
                        firstA.add(list.get(i).name);
                        count++;
                    }
                    else
                    {
                        //System.out.println(list.get(i).name + " is home team");
                        firstH.add(list.get(i).name);
                        count++;
                    }
                }
                else
                {
                    break;
                }
                i++;
            }

            // first place output
            
            if(!firstA.isEmpty())
            {
                Collections.sort(firstA);
                x = firstA.toString().replace("]", "");
                x = x.toString().replace("[", "");

                if(firstH.isEmpty())
                {
                    System.out.println(max1 + "\t" + x);
                }
            }
            if(!firstH.isEmpty())
            {
                Collections.sort(firstH);

                y = firstH.toString().replace("]", "");
                y = y.toString().replace("[", "");

                if(!firstA.isEmpty())
                {
                    x += ", ";
                    System.out.println(max1 + "\t" + x + y);
                }
                else
                {
                    System.out.println(max1 + "\t" + y);
                }      
            }
            
            

            // second place leaders 

            
            if(count < 3)
            {
                mid1 = list.get(i).hitBy;
                ArrayList<String> secondA = new ArrayList<>();
                ArrayList<String> secondH = new ArrayList<>();

                while(i < list.size())
                {
                    if(mid1 == list.get(i).hitBy)
                    {
                        if(!list.get(i).homeTeam)
                        {
                            secondA.add(list.get(i).name);      // either add player to home or away team list for output
                            count++;
                        }
                        else
                        {
                            secondH.add(list.get(i).name);
                            count++;
                        }
                    }
                    else
                    {
                        break;
                    }
                    i++;
                }

                // second place output

                if(!secondA.isEmpty())
                {
                    Collections.sort(secondA);
                    x = secondA.toString().replace("]", "");
                    x = x.toString().replace("[", "");

                    if(secondH.isEmpty())
                    {
                        System.out.println(mid1 + "\t" + x);
                    }
                }
                if(!secondH.isEmpty())
                {
                    Collections.sort(secondH);
                    y = secondH.toString().replace("]", "");
                    y = y.toString().replace("[", "");

                    if(!secondA.isEmpty())
                    {
                        x += ", ";
                        System.out.println(mid1 + "\t" + x + y);
                    }
                    else
                    {
                        System.out.println(mid1 + "\t" + y);
                    }      
                }

                // third place leaders                
                if(count < 3)
                {
                    low1 = list.get(i).hitBy;
                    ArrayList<String> thirdA = new ArrayList<>();
                    ArrayList<String> thirdH = new ArrayList<>();

                    while(i < list.size())
                    {
                        if(low1 == list.get(i).hitBy)
                        {
                            if(!list.get(i).homeTeam)
                            {
                                thirdA.add(list.get(i).name);      // either add player to home or away team list for output
                                count++;
                            }
                            else
                            {
                                thirdH.add(list.get(i).name);
                                count++;
                            }
                        }
                        else
                        {
                            break;
                        }
                        i++;
                    }

                    if(!thirdA.isEmpty())
                    {
                        Collections.sort(thirdA);
                        x = thirdA.toString().replace("]", "");
                        x = x.toString().replace("[", "");
    
                        if(thirdH.isEmpty())
                        {
                            System.out.println(low1 + "\t" + x);
                        }
                    }
                    if(!thirdH.isEmpty())
                    {
                        Collections.sort(thirdH);
                        y = thirdH.toString().replace("]", "");
                        y = y.toString().replace("[", "");
    
                        if(!thirdA.isEmpty())
                        {
                            x += ", ";
                            System.out.println(low1 + "\t" + x + y);
                        }
                        else
                        {
                            System.out.println(low1 + "\t" + y);
                        }      
                    }

                    // empty lists to be reused
                    firstA.clear();
                    firstH.clear();

                    secondA.clear();
                    secondH.clear();

                    thirdA.clear();  
                    thirdH.clear();
                }
                else
                {
                    // empty lists to be reused
                    firstA.clear();
                    firstH.clear();

                    secondA.clear();
                    secondH.clear();
                }
            }
            else
            {
                firstA.clear();
                firstH.clear();
            }




            scnr.close();
            inputFile.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to read file");
           
        } 


    } // end of main()


    /*******************************************************
     *                    getFileData                      *
     * This function loops to get input for the user file  *
     * then creates player objects and inserts them in     *
     *              the second hashtable                   *
     *******************************************************/
    public static ArrayList<Player> getFileData(Scanner inFS, Hashtable<String, String> ht1, Hashtable<String, Player> ht2) {
        String currWord;
        String names = "";
        String stat;
        String[] strArr = new String[3];
        Player obj;
        ArrayList<Player> players = new ArrayList<>();



        while(inFS.hasNext()) 
        {
            currWord = inFS.nextLine(); // get line of input from file 
            
            // split the line of strings from input by the space that seperates team, name, and batting stat 
            strArr = currWord.split(" ");

            if(strArr.length == 3)    // it should equal 3 but just to add some minor input validation...
            {
                if(!names.contains(strArr[1]))     // this means it's a new player
                {
                    names += strArr[1] + " ";   // add their name to the collection of names that have been found so far

                    // create new player
                    if(strArr[0].equals("H"))
                    {
                        obj = new Player(strArr[1], true, 0, 0, 0, 0, 0, 0, 0);    // home team
                    }
                    else
                    {
                        obj = new Player(strArr[1], false, 0, 0, 0, 0, 0, 0, 0);   // away team
                    }
                     
                    // add object to hashtable #2
                    ht2.put(strArr[1], obj);

                    // add object to player list
                    players.add(obj);
                }

                obj = ht2.get(strArr[1]);   // searches second hashtable for player name (key) and returns the associated object (value)

                stat = ht1.get(strArr[2]);  // searches first hashtable of stat codes to determine which batting stat to update

                if(stat.equals("OUTS"))
                {
                    obj.updatePlayer(0, 1, 0, 0, 0, 0, 0);                   
                }
                else if(stat.equals("HITS"))
                {
                    obj.updatePlayer(1, 0, 0, 0, 0, 0, 0);
                }
                else if(stat.equals("STRIKEOUT"))
                {
                    obj.updatePlayer(0, 0, 1, 0, 0, 0, 0);
                }
                else if(stat.equals("WALK"))
                {
                    obj.updatePlayer(0, 0, 0, 1, 0, 0, 0);
                }
                else if(stat.equals("ERRORS"))
                {
                    obj.updatePlayer(0, 0, 0, 0, 0, 0, 1);
                }
                else if(stat.equals("SACRIFICE"))
                {
                    obj.updatePlayer(0, 0, 0, 0, 0, 1, 0);
                }
                else if(stat.equals("HITBYPITCH"))
                {
                    obj.updatePlayer(0, 0, 0, 0, 1, 0, 0);
                }

                
                
            }

        }

        return players;
    
    } // end of getFileData()


    /*******************************************************
     *                    getKeyFileData                   *
     *  This function loops to get input for the key file  *
     *        to create the stat code hashtable            *
     *******************************************************/
    public static void getKeyFileData(Scanner inFS, Hashtable<String, String> ht1) {

        String cur;
        String key = "";
        String value = "";
        

        while (inFS.hasNext()) 
        {
            cur = inFS.nextLine();

            if(cur.contains("##"))      // if it has a "#" then use it as a value
            {
                cur = cur.replaceAll("##", "");
                cur = cur.replaceAll(" ", "");
                value = cur;
                //System.out.println("key: " + cur);
            }
            else        // otherwise use it as a key
            {
                if(!cur.equals(""))
                {
                    key = cur;
                    //System.out.println("key: " + key + "\tvalue: " + value);
                    ht1.put(key, value);
                }
            }

        }

    } // end of getKeyFileData()
}
