module ETL
  def self.transform(origin)
    origin.map do |num, array|
      array.map do |key|
        [key.downcase,  num]
      end
    end.flatten(1).to_h
  end
end
