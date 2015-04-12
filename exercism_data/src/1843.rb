class Hamming #copied from online

  def compute(strand1, strand2)
    diff = 0
    strand1.chars.each_with_index do |dna, index|
      if index < strand2.length
        diff+=1 if dna != strand2[index]
      end
    end
    diff
  end
end
      

#ruby comment

# Prompt for input strand, strand1
#puts 'Gimme dat strand (first)'
#strand1 = gets.chomp
# prompt for input strand, strand2
#puts 'And another!'
#strand2 = gets.chomp
# compare the strands character by character
#puts strand1
#puts strand2
#puts 'ITERATE'
#report back simple output with number of characters that are different
