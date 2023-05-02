%read csv to get salted data
data = csvread('OctaveSalter.csv');

%extract x and y values
x = data(:,1);
y = data(:,2);

%creates the window for smoothing
window = 3;

%smoothes data
smoothed_y = zeros(size(y));
for i = 1:length(y)
    start_index = max(i - window, 1);
    end_index = min(i + window, length(y));
    smoothed_y(i) = mean(y(start_index:end_index));
end

plot(x,smoothed_y)

%labels for graph
title('X versus Y graph Smoothed')
set(gca, 'fontsize', 16)
xlabel('X Values')
ylabel('Y Values')

%adds a grid to the graph
grid on

smoothed_data = [x, smoothed_y];
csvwrite('OctaveSmoother.csv', smoothed_data);