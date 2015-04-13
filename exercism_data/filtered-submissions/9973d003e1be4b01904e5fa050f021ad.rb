def compute one, two




    [ one.length, two.length ].min - one.split(//).zip(two.split(//)).inject(0) do |sum, pair|
      pair[0] == pair[1] ? sum +=1 : sum

    end
  end