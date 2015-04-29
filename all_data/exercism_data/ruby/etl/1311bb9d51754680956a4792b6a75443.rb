class ETL
  class << self
    def transform(old)
      old.each_with_object(Hash.new){|(key,value),inverse| value.each{|new_val| inverse[new_val.downcase]=key}}
    end
  end
end
