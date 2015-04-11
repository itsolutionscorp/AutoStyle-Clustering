class ETL
  def self.transform(old)
    transformed = {}
    old.each do |score, letters|
      letters.each do |l|

        transformed[l.downcase] = score
      end
    end
    transformed
  end
end
