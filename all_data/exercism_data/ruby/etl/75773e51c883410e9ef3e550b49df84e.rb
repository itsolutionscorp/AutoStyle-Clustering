module ETL
  class << self
    def transform(v)
      out = Hash.new

      v.each do |k, o|
        o.each do |l|
          out = out.merge({ l.downcase => k })
        end
      end

      return out
    end
  end
end
