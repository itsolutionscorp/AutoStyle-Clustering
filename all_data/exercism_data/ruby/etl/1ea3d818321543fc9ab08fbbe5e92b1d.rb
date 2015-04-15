class ETL
  def self.transform(old)
    nice = []
    old.invert.each_pair do |letters, score|
      letters.each { |letter| nice << [letter.downcase, score] }
    end
    nice.to_h
  end
end
