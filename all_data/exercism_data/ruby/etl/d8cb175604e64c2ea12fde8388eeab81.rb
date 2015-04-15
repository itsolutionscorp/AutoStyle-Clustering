class ETL
  def self.transform old
    transformed = {}
    old.each do | score, letters |
      letters.each do | letter |
        transformed[letter.downcase] = score
      end
    end
    transformed
  end
end
