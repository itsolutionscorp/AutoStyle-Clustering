class ETL

def self.transform(input)
 new_system_hash = {}
  input.each do |score,letter_array|
    letter_array.each do |new_letters|
      new_system_hash[new_letters.downcase] = score
    end

  end
  new_system_hash
end

end
