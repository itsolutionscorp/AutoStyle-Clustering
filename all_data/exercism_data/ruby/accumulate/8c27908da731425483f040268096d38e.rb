class Array
  def accumulate
    r = []

    each do |o|
      r.push yield o
    end

    r
  end
end
