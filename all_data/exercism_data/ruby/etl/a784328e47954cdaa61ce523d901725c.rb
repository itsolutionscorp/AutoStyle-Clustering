class ETL
  def self.transform(old)
    old.each_with_object({}) do |(point, letters), hsh|
      letters.each { |l| hsh[l.downcase] = point }
    end
  end
end
