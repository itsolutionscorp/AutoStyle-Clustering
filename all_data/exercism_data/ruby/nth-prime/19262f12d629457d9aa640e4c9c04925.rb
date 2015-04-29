require 'prime'

module PrimeIndexer

  PRIME_CACHE = [] 
  PRIME_INDEXER = Prime.new 
  
  def nth(index)
    raise ArgumentError if index < 1
    PRIME_CACHE << PRIME_INDEXER.next while PRIME_CACHE.size < index
    PRIME_CACHE[index-1]
  end

end

Prime.extend PrimeIndexer
