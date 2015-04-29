class Hamming
  def self.compute(reference, examinee)
    hd = HammingDistance.new(reference: reference, examinee: examinee)
    hd.distance
  end
end

class HammingDistance

  attr_reader :reference, :examinee, :distance

  def initialize(args={})
    @reference = args[:reference]
    @examinee = args[:examinee]
    @distance = 0
    calculate_length
  end

  def calculate_length
    (0..pairs_to_count).each do |string_index|
      if examinee[string_index] != reference[string_index]
        increment_distance
      end
    end
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


### My original Perl/procedural implementation.

# class Hamming
#   def self.compute(reference, examinee)
#     if reference.length > examinee.length
#       run_length = examinee.length
#     else
#       run_length = reference.length
#     end
#
#     run_length -= 1
#
#     hamming_distance = 0
#
#     (0..run_length).each do |string_index|
#       if examinee[string_index] != reference[string_index]
#         hamming_distance += 1
#       end
#     end
#
#     hamming_distance
#   
#   end
#
# end
