class ETL
  def self.transform old_letters
    alphabet = {}
    old_letters.each do | k,v |
      v.each do | letter |
        alphabet[letter.downcase] = k
      end
    end
    alphabet
  end
end
