class Hamming

  def compute(first_chain, second_chain)
    first_chain.chars.zip(second_chain.chars).reduce(0) do |sum, (x, y)|
      break sum if !x || !y
      x != y ? sum + 1 : sum         
    end
  end

end
