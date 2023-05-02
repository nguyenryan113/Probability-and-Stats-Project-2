%generate x and y data
x = 0:10;
y = 2*x+5;
plot(x, y);

%labels for graph
title('X versus Y graph')
set(gca, 'fontsize', 16)
xlabel('X Values')
ylabel('Y Values')

%change limits of axis
axis([-1 12 0 30])

% independently change the axis
%xlim([-1 12])
%ylim([0 30])

%adds a grid to the graph
grid on

csvwrite('OctavePlotter.csv',[x;y]')