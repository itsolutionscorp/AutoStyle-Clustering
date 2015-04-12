class Hamming
  def compute one, two
    # one.size - (one.match(two) ? one.match(two).size :  0)
    # one.zip(two).each do |pair|
    #   pair[0] == pair[1]
    # end
    [ one.length, two.length ].min - one.split(//).zip(two.split(//)).inject(0) do |sum, pair|
      pair[0] == pair[1] ? sum +=1 : sum
    # end - (one.count)
    end
  end
end
