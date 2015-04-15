module ETL
  extend self

  def transform(legacy_hash)
    legacy_hash.each_with_object({}) do |(key, values), result|
      values.each { |v| result[v.downcase] = key }
    end
  end

end
