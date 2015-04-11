class ETL
  def self.transform(old)
    array = []
    old.each_pair { |key, val| val.join.downcase.each_char { |char| array << Hash[char => key] } }
    array.reduce Hash.new, :merge
  end
end
