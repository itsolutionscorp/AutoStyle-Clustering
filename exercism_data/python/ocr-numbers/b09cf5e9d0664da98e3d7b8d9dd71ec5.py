def vals():
	charNums = [
	 "    _  _     _  _  _  _  _  _ ",
	 "  | _| _||_||_ |_   ||_||_|| |",
	 "  ||_  _|  | _||_|  ||_| _||_|", 
	 "                              "] 
	nums = {(i+1)%10:[charNums[j][3*i:3+3*i] for j in range(4)] for i in range(10)}
	vals = [[charNums[j][3*i:3+3*i] for j in range(4)] for i in range(10)]
	return [nums,vals]

def grid(inp):
	nums,val = vals()
	try:
		return nums[int(inp)]
	except ValueError:
		raise ValueError("Error, malformed input.")

def number(inp):
	nums,val = vals()
	try:
		return str((val.index(inp) + 1) %10)
	except:
		if len(inp) == 4 and sum(len(inp[i]) for i in range(4))==12:
			return "?"
		raise ValueError("Error, malformed input.")
