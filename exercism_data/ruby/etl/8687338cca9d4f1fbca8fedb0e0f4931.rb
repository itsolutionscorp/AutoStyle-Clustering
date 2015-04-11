class ETL
  def self.transform(old)
    new_hash = {}
    old.each do |number, array_of_letters|
      array_of_letters.each do |letter|
        new_key = letter.downcase
      new_value = number
      new_hash[new_key] = new_value
      end
    end
    new_hash
  end
end
