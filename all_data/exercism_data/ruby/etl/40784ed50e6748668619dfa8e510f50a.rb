class ETL
  class << self
    def transform(old)
      old.invert.inject({}) do |new_hsh, (letters, score)|
        letters.each do |letter|
          new_hsh[letter.downcase] = score
        end
        new_hsh
      end
    end
  end
end
