module ETL
  extend self

  def transform(input)
    input.each_with_object({}) do |(source, data), db|
      data.each {|datum| db[datum.downcase] = source}
    end
  end
end
