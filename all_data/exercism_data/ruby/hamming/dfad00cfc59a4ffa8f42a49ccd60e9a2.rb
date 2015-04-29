class Hamming

  attr_reader :reference, :examinee, :distance

  def self.compute (reference, examinee)
    new(reference, examinee).distance 
  end

  def initialize (reference, examinee)
    @reference = reference
    @examinee = examinee
    @distance = calculate_length
  end

  def calculate_length 
    (0...pairs_to_count).count do |string_index|
      examinee[string_index] != reference[string_index]
    end
  end

  def pairs_to_count
    [reference.length, examinee.length].min
  end

end
