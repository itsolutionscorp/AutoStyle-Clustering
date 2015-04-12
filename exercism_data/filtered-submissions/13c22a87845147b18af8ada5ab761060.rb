class Hamming
  def compute(reference, examinee)
    
    pairs_to_count = [reference.length, examinee.length].min

    (0...pairs_to_count).count do |string_index|
      examinee[string_index] != reference[string_index]
    end

  end
end
