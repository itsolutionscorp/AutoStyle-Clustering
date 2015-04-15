def hamming_distance(stranda,strandb)
counter = 0
while stranda.length < strandb.length
    strandb.chop!
end       
while strandb.length < stranda.length
    stranda.chop!
end
strand1 = stranda.split(//)
strand2 = strandb.split(//)
strand1.zip(strand2) {|x,y| if x != y then counter +=1 end}
puts "Hamming Distance is #{counter}."
end

hamming_distance("A", "A")
hamming_distance("A","G")
hamming_distance("AG","CT")
hamming_distance("AT","CT")
hamming_distance("GGACG", "GGTCG")
hamming_distance("AGAGACTTA", "AAA")
hamming_distance("AGG", "AAAACTGACCCACCCCAGG")
hamming_distance("GATACA", "GCATAA")
hamming_distance("GGACGGATTCTG", "AGGACGGATTCT")
