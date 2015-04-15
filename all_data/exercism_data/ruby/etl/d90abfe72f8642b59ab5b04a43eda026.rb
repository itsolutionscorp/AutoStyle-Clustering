class ETL

  def self.transform(old)
    transformed = {}
    old.each do |point, letter|
      letter.each do |l|
        transformed[l.downcase] = point
      end
    end
    transformed
  end

end
