a_strand = "GAGCCTACTAACGGGAT"
b_strand = "CATCGTAATGACGGCCT"

if a_strand.length != b_strand.length
  puts 'Strands are not the same length, exiting...'
  exit
end

len = 0
count = 0
while len <= a_strand.length do
    count += 1 if a_strand[len] != b_strand[len]
    len += 1
end

puts 'The Hamming Count is: ' + count.to_s
