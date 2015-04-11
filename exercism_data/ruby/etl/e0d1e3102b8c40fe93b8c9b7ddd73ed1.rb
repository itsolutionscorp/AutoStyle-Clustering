class ETL
  def self.transform(old)
    nice = []
    old.invert.each_pair do |k, v|
      k.each { |letter| nice << [letter.downcase, v] }
    end
    nice.to_h
  end
end
