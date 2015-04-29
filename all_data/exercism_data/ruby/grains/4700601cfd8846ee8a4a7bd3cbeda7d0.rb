class Grains

  def square(n)
  	2**(n-1)
  end

  def total
  	square(65) - 1
  end

  # following recursive function is much faster than plain version
  # Because for n = x^N, it uses O(lgN) powering and O(lgN) multiplications
  # Achieving O(lgN*lgN) time complexity (ignoring powering/multiplications time themselves)
  # That is better than O(N) multiplications if we use plain version above
  
  # def square(n)
  # 	square_recursive(n-1)
  # end

  # def square_recursive(n)
  # 	if(n == 0)
  # 		1
  # 	elsif(n == 1)
  # 		2
  #   elsif(n % 2 == 0)
  #   	square_recursive(n/2) * square_recursive(n/2)
  #   else
  #       2 * square_recursive(n/2) * square_recursive(n/2) 
  #   end
  # end

end
