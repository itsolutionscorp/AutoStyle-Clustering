class Hamming
  def self.compute first_strand, second_strand 
      nucleotides_paired_up_from(first_strand, second_strand)\
       .reject{|nucleotides| nucleotides[0] == nucleotides[1]}\
       .size
  end

  def self.nucleotides_paired_up_from first_strand, second_strand
    min_length = min_length_between first_strand, second_strand
    first_strand.chars.zip(second_strand.chars).take(min_length)
  end

  def self.min_length_between first_strand, second_strand
    [first_strand, second_strand].map(&:length).min
  end

  private_class_method :nucleotides_paired_up_from, :min_length_between
end
