class ETL
  def self.transform(old)
    new = {}
    old.each do |score, letters|
      letters.map { |l| new[l.downcase] = score }
    end
    new
  end
end
