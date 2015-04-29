class ETL
  def self.transform old
    old.each_with_object({}) do |(k, v), a|
      v.each { |vv| a[vv.downcase] = k }
    end
  end
end
