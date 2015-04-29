class Hamming
  def Hamming.compute(one_sequence, another_sequence)
    if one_sequence.length != another_sequence.length
      raise ArgumentError "required same length: #{one_sequence.length} != #{another_sequence.length}"
    end
    one_sequence.chars.zip(another_sequence.chars).count do |one_base, another_base|
      one_base != another_base
    end
  end
end
