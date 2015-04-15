module ETL
  extend self

  def transform(old)
    old.each_with_object({}) { |(key, values), hsh|
      values.each { |value| hsh.update(value.downcase => key) }
    }
  end
end
