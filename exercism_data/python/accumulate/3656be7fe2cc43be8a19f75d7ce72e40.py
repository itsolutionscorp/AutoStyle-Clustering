def accumulate(iterable, f):
    return [f(item) for item in iterable]

# def accumulate(iterable, f):
#     iterator = iter(iterable)
#     acc = []
#     try:
#         while True:
#             item = next(iterator)
#             acc.append(f(item))
#     except StopIteration:
#         return acc
