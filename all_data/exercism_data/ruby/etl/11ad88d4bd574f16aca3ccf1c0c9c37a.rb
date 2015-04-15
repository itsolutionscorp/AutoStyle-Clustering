class ETL
  def self.transform(hash)
    hash.each_with_object({}) do |(point, point_classes), new_hash|
      point_classes.each do |point_class|
        new_hash.merge!(point_class.downcase => point)
      end
    end
  end
end
