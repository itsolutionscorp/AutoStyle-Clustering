def combine_anagrams(words)
  myhash = {}
  words.each do |entry|
    tst = entry.downcase.split(//).sort.join
    if (myhash[tst])
      myhash[tst] << entry
    else
      myhash[tst] = [entry]
    end
  end
  myarr = []
  myhash.each_value do |val|
    myarr << val 
  end
  myarr
end

