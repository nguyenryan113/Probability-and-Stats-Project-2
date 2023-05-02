%read csv to get data
M = csvread('OctavePlotter.csv')

%extract the x and y values
x = M(:,1)
y = M(:,2)

for i=1:10
  y(i) = y(i) + randi([-30 30])
end

plot(x,y)

%labels for graph
title('X versus Y graph Salted')
set(gca, 'fontsize', 16)
xlabel('X Values')
ylabel('Y Values')

%adds a grid to the graph
grid on

csvwrite('OctaveSalter.csv',[x,y])