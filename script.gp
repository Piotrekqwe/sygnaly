set style fill solid 1.00 border lt -1
set style data histograms
set xtics border in scale 0,0 nomirror rotate by -45  autojustify
#set title "histogram" 
set yrange [ 0 : 1 ]
plot 'data.txt' using 2:xtic(1) ti col

