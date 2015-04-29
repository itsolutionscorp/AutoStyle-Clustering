class ETL
  def self.transform(old)
    array = []
    old.each_pair { |key, val| val.join.downcase.each_char { |char| array << [char, key] } }
    array.to_h
  end
end
