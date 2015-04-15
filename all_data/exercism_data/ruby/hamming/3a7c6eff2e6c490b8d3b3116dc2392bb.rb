module Hamming
  # Ruby gotcha: zip supplies nil values first array is longer than others.
  # http://www.ruby-doc.org/core-2.1.3/Array.html
  #
  # I got bitten because I expected Haskell behavior, where all extra
  # values are ignored after the shortest list is exhausted.
  #
  # I think this is the cleanest solution, but it is also clearly
  # inefficient, given my assumption about Ruby compiler implementations
  # and what kind of optimizations they can do: zip will actually
  # allocate a new array and fill it, and count is a method call,
  # and a block is allocated as well to pass to it.
  #
  # For reference: a Haskell equivalent is very efficient because of
  # stream fusion, enabled by the fact that Haskell is purely functional
  # and therefore the compiler can be told to optimize away the zip, and
  # also I expect the tiny equality comparison to be inlined, such that
  # the final compiled code is just a single while-loop in the machine
  # code that iterates over the two character lists while keeping the
  # current count in a register and increments it: close to what C
  # would generate for a hand-rolled loop.
  #
  # http://hackage.haskell.org/package/stream-fusion
  def self.compute(dna1, dna2)
    if dna1.length <= dna2.length
      short = dna1
      long = dna2
    else
      short = dna2
      long = dna1
    end

    short.chars
      .zip(long.chars)
      .count { |c1, c2| c1 != c2 }
  end
end
