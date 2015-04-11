class ETL
  def self.transform(hash)
    new_hash = {}
    hash.each do |point, point_classes|
      point_classes.each do |point_class|
        new_hash.merge!(point_class.downcase => point)
      end
    end
    new_hash
  end
end
