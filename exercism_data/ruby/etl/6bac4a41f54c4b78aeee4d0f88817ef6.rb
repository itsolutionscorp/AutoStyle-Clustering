require 'pry'

class ETL
  def self.transform(old)
    old.each_with_object({}) do |(key, value), transform|
      value.each do |v| transform[v.downcase] = key
      end
    end
  end
end
