def sort_str(str)
  tarr = []
  tstr = ""
  str.each_char { |c| tarr.push(c.ord) }
  tarr.sort.each { |i| tstr = (tstr + i.chr) }
  tstr
end

def combine_anagrams(words)
  myhash = Hash.new
  words.each do |word|
    s = sort_str(word.downcase)
    myhash.has_key?(s) ? (myhash[s].push(word)) : (myhash[s] = [word])
  end
  myhash.values
end

