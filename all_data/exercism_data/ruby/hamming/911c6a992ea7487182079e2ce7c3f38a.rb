class Hamming

  attr_reader :reference, :examinee, :distance

  def self.compute(reference, examinee)
    new(reference, examinee).calculate_length 
  end

  def initialize(reference, examinee)
    @distance = 0
    @reference = reference
    @examinee = examinee
  end

  def calculate_length
    (0..pairs_to_count).each do |string_index|
      if examinee[string_index] != reference[string_index]
        increment_distance
      end
    end
    # TODO: Originally this didn't return anything. This
    # iteration was the simplest thing do to to move 
    # everything directly into the Hamming class. So, 
    # ugly but it works.
    distance
  end

  def increment_distance
    @distance += 1
  end

  def pairs_to_count
    if reference.length > examinee.length
      examinee.length - 1
    else
      reference.length - 1
    end
  end

end
