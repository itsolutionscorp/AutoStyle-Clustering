# Using enumerable, but not the methods mentioned in
# the Readme, which directly implement the keep
# and discard behaviour.
#
# I felt like this was okay, becaues to remove it
# is just to make `to_keep` a local variable, which
# is a trivial change. I figure it's okay to use `#each`
# since that's actually implemented by Array and not
# Enumerable. Alternative is to access elements by index,
# but even then, we're using `Array#[]`, so it seems
# equally cheating as `Array#each`
class Array
  def keep
    each_with_object Array.new do |element, to_keep|
      to_keep << element if yield element
    end
  end

  def discard
    keep { |element| not yield element }
  end
end
