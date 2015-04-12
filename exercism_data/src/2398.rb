# Computer hamm difference between two DNA strands
# Author::    Bryan Paxton  (mailto:starbelly@pobox.com)

# Hamming class
class Hamming
  def compute(seq_a, seq_b)
    hamm = 0
    # Covert each strand into an array of chars and merge
    # both into an array of arrays.
    # Strand ABC and strand  BCA becomes [ ['a', 'b'], ['b', 'c'], ['c','a'] ]
    seq_a.each_char.zip(seq_b.each_char).each do |a, b|
      break if a.nil? || b.nil?
      hamm += 1 if a != b
    end
    hamm
  end
end
