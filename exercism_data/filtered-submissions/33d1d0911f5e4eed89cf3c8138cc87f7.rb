def compute(first, second)
    count = 0
    first.scan(/./).zip(second.scan(/./)).each do |match|
      count += 1 if !match[0].nil? && !match[1].nil? && match[0] != match[1]
    end
   count
  end