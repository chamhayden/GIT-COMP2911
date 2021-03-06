import java.util.ArrayList;

public class PrintGraph {

	public PrintGraph(AdjListGraph<DualPoint> graph)
	{
		ArrayList<ArrayList<DualPoint>> nodes = graph.getNodes();
		int maxX = 0;
		int maxY = 0;
		for (int i = 0; i < nodes.size(); i++)
		{
			if (maxX < nodes.get(i).get(0).getFromX())
			{
				maxX = nodes.get(i).get(0).getFromX();
			}
			if (maxX < nodes.get(i).get(0).getToX())
			{
				maxX = nodes.get(i).get(0).getToX();
			}
			if (maxY < nodes.get(i).get(0).getFromY())
			{
				maxY = nodes.get(i).get(0).getFromY();
			}
			if (maxY < nodes.get(i).get(0).getToY())
			{
				maxY = nodes.get(i).get(0).getToY();
			}
		}
		
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		
		for (int i = 0; i <= maxY; i++)
		{
			ArrayList<String> temp = new ArrayList<String>();
			data.add(i, temp);
			for (int j = 0; j <= maxX; j++)
			{
				String temp2 = new String(" ** ");
				data.get(i).add(j, temp2);	
			}
		}
		
		for (int i = 0; i <= maxY; i++)
		{
			ArrayList<String> temp = new ArrayList<String>();
			data.add(i, temp);
			for (int j = 0; j <= maxX; j++)
			{
				String val = new String();
				boolean done = false;
				val = " --";
				for (int k = 0; k < nodes.size(); k++)
				{
					
					if (nodes.get(k).get(0).getFromX() == ((int)j) && nodes.get(k).get(0).getFromY() == ((int)i))
					{
						val = " F" + k + "";
						done = true;
					}
					if (nodes.get(k).get(0).getToX() == ((int)j) && nodes.get(k).get(0).getToY() == ((int)i))
					{
						val = " T" + k + "";
						done = true;
					}
					if (nodes.get(k).get(0).getToX() == ((int)j) && nodes.get(k).get(0).getToY() == ((int)i))
					{
						if (nodes.get(k).get(0).getFromX() == ((int)j) && nodes.get(k).get(0).getFromY() == ((int)i))
						{
							val = " S" + k + "";
							done = true;
						}
					}
					if (!done)
					{
						int xFrom = nodes.get(k).get(0).getFromX();
						int xTo = nodes.get(k).get(0).getToX();
						int yFrom = nodes.get(k).get(0).getFromY();
						int yTo = nodes.get(k).get(0).getToY();
						if (!(yTo == yFrom && xTo == xFrom))
						{
							if (yTo == yFrom)
							{
								if (j > xFrom && j < xTo && i == yTo)
								{
									val = " **";
								}
							}
							else if (xTo == xFrom)
							{
								if (i > yFrom && i < yTo && j == xTo)
								{
									val = " **";
								}
							}
							else
							{
								int gradient = (yTo - yFrom) / (xTo - xFrom);
								
								if (i >= (gradient*(j - xTo) + yTo) && i < (gradient*(j - xTo) + yTo + gradient) && j >= xFrom && j <= xTo)
								{
									val = " **";
									
								}
							}
						}
						
					}
				}
				data.get(i).add(j, val);	
			}
		}
		
		for (int i = maxY; i >= 0; i--)
		{
			for (int j = 0; j <= maxX; j++)
			{
				System.out.print(data.get(i).get(j));
			}
			System.out.print("\n");
		}
	}
}
