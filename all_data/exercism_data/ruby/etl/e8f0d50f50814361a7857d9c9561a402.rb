class ETL

  def self.transform(old)
    result = {}

    old.keys.each do |elem|

      ans = {}
      bin = old[elem]
      for i in bin
        ans[i.downcase] = elem
      end
      result.merge!(ans)
    end
    return result
  end
end


# ETL.transform({1 => ['A']})
