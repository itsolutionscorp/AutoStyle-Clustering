module ETL
  def self.transform old
    old.each_with_object({}) do |(key, values), reversed|
      values.map(&:downcase)
            .each { |value| reversed[value] = key }
    end
  end
end
