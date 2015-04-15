class Hamming

  def self.compute(a_string, b_string)
    error_count = 0
    index_counter = 0
    while index_counter < a_string.length
      if a_string[index_counter] == nil || b_string[index_counter] == nil
        return error_count
      elsif a_string[index_counter] != b_string[index_counter]
        error_count += 1
      end
      index_counter += 1
    end
    return error_count 
  end

end
