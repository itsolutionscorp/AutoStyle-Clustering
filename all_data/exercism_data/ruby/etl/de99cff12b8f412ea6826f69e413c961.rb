class ETL
  def self.transform old_data
    result = {}
    for score, chars_array in old_data
      for char in chars_array
        result[char.downcase] = score
      end
    end

    result
  end
end
