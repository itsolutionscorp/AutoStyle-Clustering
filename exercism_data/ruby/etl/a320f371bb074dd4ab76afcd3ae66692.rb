class ETL
  def self.transform(hash)
    hash.reduce({}) do |new_hash, (point, point_classes)|
      point_classes.each do |point_class|
        new_hash.merge!(point_class.downcase => point)
      end
      new_hash
    end
  end
end
